from repo_parse.config import INTERFACE_BROTHER_RELATIONS_PATH
from repo_parse.utils.data_processor import save_json


class InterfaceResolver:
    def __init__(self, class_metainfo, interface_metainfo):
        self.class_metainfo = class_metainfo
        self.interface_metainfo = interface_metainfo

    def resolve_interface_brother_relation(self, save: bool = True):
        interface_map = {interface['name']: interface for interface in self.interface_metainfo}
        def collect_methods(interface_name, visited=None):
            if visited is None:
                visited = set()
            if interface_name in visited:
                return []
            visited.add(interface_name)

            interface = interface_map.get(interface_name)
            if not interface:
                return []

            methods = []
            for method in interface.get('methods', []):
                method_parts = method.split('.')
                method_signature = method_parts[-1] if method_parts else method
                methods.append(method_signature)

            for parent_interface in interface.get('superclasses', []):
                parent_interface_name = self.get_interface_name_by_uri(parent_interface)
                if parent_interface_name:
                    parent_methods = collect_methods(parent_interface_name, visited)
                    methods.extend(parent_methods)

            return methods

        result = []
        for interface in self.interface_metainfo:
            interface_name = interface['name']
            all_methods = collect_methods(interface_name)

            implementing_classes = []
            for cls in self.class_metainfo:
                if interface_name in cls.get('super_interfaces', []):
                    implementing_classes.append(cls['uris'])

            interface_dict = {
                "uris": interface['uris'],
                "name": interface_name,
                "methods": all_methods,
                "implementations": implementing_classes
            }
            result.append(interface_dict)
        
        if save:
            save_json(file_path=INTERFACE_BROTHER_RELATIONS_PATH, data=result)

        return result

    def get_interface_name_by_uri(self, uri):
        for interface in self.interface_metainfo:
            if interface['uris'] == uri:
                return interface['name']
        return None
