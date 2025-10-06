import json
from collections import defaultdict
from typing import List, Dict, Tuple, Optional

from repo_parse.config import BROTHER_RELATIONS_PATH, CLASS_METAINFO_PATH, INHERIT_TREE_PATH
from repo_parse.utils.data_processor import load_json

class inherit_resolver:
    def __init__(self, class_metainfo_path: str = CLASS_METAINFO_PATH, 
                 brother_relations_path: str = BROTHER_RELATIONS_PATH, 
                 inherit_tree_path: str = INHERIT_TREE_PATH):
        self.class_metainfo_path = class_metainfo_path
        self.brother_relations_path = brother_relations_path
        self.inherit_tree_path = inherit_tree_path
        
        self.inherit_relations: List[Tuple[str, str]] = [] 
        self.childs: Dict[str, List[str]] = defaultdict(list)
        self.parents: Dict[str, str] = {}
        self.brother_relations: Dict[str, List[str]] = {}
        
        if self.load_inherit_tree():
            print("继承树数据已从持久化文件加载")
        else:
            self.class_metainfo = load_json(self.class_metainfo_path)
            self.build_inherit_tree()
            self.resolve_brother_relation()
            self.save_inherit_tree()
    
    def load_inherit_tree(self) -> bool:
        try:
            with open(self.inherit_tree_path, 'r') as f:
                data = json.load(f)
                self.inherit_relations = data.get('inherit_relations', [])
                self.childs = defaultdict(list, data.get('childs', {}))
                self.parents = data.get('parents', {})
                self.brother_relations = data.get('brother_relations', {})
            return True
        except FileNotFoundError:
            return False
    
    def save_inherit_tree(self):
        data = {
            'inherit_relations': self.inherit_relations,
            'childs': dict(self.childs),
            'parents': self.parents,
            'brother_relations': self.brother_relations
        }
        with open(self.inherit_tree_path, 'w') as f:
            json.dump(data, f, indent=4)
    
    def save_json(self, file_path: str, data: dict):
        with open(file_path, 'w') as f:
            json.dump(data, f, indent=4)
    
    def build_inherit_tree(self):
        for class_info in self.class_metainfo:
            class_name = class_info['name']
            parent_name = class_info['superclasses']
            if parent_name:
                self.inherit_relations.append((class_name, parent_name))
                self.childs[parent_name].append(class_name)
                self.parents[class_name] = parent_name
    
    def resolve_brother_relation(self, save: bool = True):
        for parent, children in self.childs.items():
            if len(children) < 2:
                continue
            for child in children:
                if child not in self.brother_relations:
                    self.brother_relations[child] = []
                for sibling in children:
                    if sibling != child:
                        self.brother_relations[child].append(sibling)
        
        if save:
            self.save_json(self.brother_relations_path, self.brother_relations)
    
    def get_brothers(self, class_name: str) -> List[str]:
        return self.brother_relations.get(class_name, [])
    
    def get_brothers_and_parent(self, class_name: str):
        for parent, childs in self.childs:
            if class_name in childs:
                return parent, list(set(childs) - {class_name})

    def get_parent(self, class_name: str) -> Optional[str]:
        return self.parents.get(class_name, None)
    
    def get_ancestors(self, class_name: str) -> List[str]:
        ancestors = []
        current = class_name
        while current in self.parents:
            parent = self.parents[current]
            ancestors.append(parent)
            current = parent
        return ancestors
    
    def get_children(self, class_name: str) -> List[str]:
        return self.childs.get(class_name, [])
    
    def get_all_descendants(self, class_name: str) -> List[str]:
        descendants = []
        direct_children = self.get_children(class_name)
        descendants.extend(direct_children)
        
        for child in direct_children:
            descendants.extend(self.get_all_descendants(child))
        return descendants
