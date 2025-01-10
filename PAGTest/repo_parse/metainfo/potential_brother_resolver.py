import json
from collections import defaultdict
from typing import List, Dict, Set, Tuple
from itertools import combinations

from repo_parse.config import POTENTIAL_BROTHER_RELATIONS_PATH
from repo_parse.utils.data_processor import save_json


class PotentialBrotherResolver:
    def __init__(self, class_metainfo: List[Dict]):
        self.class_metainfo = class_metainfo

    def jaccard_similarity(self, set1: Set[str], set2: Set[str]) -> float:
        intersection = set1.intersection(set2)
        union = set1.union(set2)
        if not union:
            return 0.0
        return len(intersection) / len(union)

    def resolve_potential_brothers(self, similarity_threshold: float = 1.0, save: bool = True) -> Dict[str, List[str]]:
        superclass_to_classes = defaultdict(set)
        class_to_superclass = {}

        for cls in self.class_metainfo:
            class_name = cls.get('name')
            superclasses = cls.get('superclasses', [])
            if isinstance(superclasses, str):
                superclasses = [superclasses] if superclasses.strip() else [""]
            elif isinstance(superclasses, list):
                superclasses = superclasses if superclasses else [""]
            else:
                superclasses = [""]
            parent_class = superclasses[0] if superclasses else ""
            superclass_to_classes[parent_class].add(class_name)
            class_to_superclass[class_name] = parent_class

        class_to_methods = {}
        for cls in self.class_metainfo:
            class_name = cls.get('name')
            methods = set(cls.get('methods', []))
            class_to_methods[class_name] = methods

        potential_brothers = defaultdict(set)
        all_classes = [cls['name'] for cls in self.class_metainfo]

        for cls1, cls2 in combinations(all_classes, 2):
            parent1 = class_to_superclass.get(cls1, "")
            parent2 = class_to_superclass.get(cls2, "")
            if parent1 and parent1 == parent2:
                continue

            methods1 = class_to_methods.get(cls1, set())
            methods2 = class_to_methods.get(cls2, set())
            similarity = self.jaccard_similarity(methods1, methods2)

            if similarity >= similarity_threshold:
                potential_brothers[cls1].add(cls2)
                potential_brothers[cls2].add(cls1)

        potential_brothers_dict = {cls: sorted(list(brothers)) for cls, brothers in potential_brothers.items()}
        if save:
            save_json(file_path=POTENTIAL_BROTHER_RELATIONS_PATH, data=potential_brothers_dict)

        return potential_brothers_dict

