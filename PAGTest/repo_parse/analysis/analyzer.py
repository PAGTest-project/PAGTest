from abc import ABC, abstractmethod
import json
import tiktoken

from repo_parse.llm.llm import LLM
from repo_parse.metainfo.metainfo import MetaInfo
from repo_parse import logger

class Analyzer(ABC, MetaInfo):
    def __init__(self, llm: LLM = None):
        self.llm = llm
        MetaInfo.__init__(self)
        self.tokenizer = tiktoken.get_encoding("gpt2")  
        self.token_threshold = 4096

    @abstractmethod
    def excute(self):
        pass
    
    def extract_json(self, llm_resp: str):
        """
        Extract CMakeLists from LLM raw output.
        """
        if llm_resp.startswith("```json"):
            return llm_resp.split("```json")[1].split("```")[0]
        elif llm_resp.startswith("```"):
            return llm_resp.split("```")[1].split("```")[0]
        return llm_resp
    
    def extract(self, full_response):
        """
        Extract fuzztest driver from LLM raw output.
        """
        resp_json: str = self.extract_json(full_response)
        try:
            resp: dict = json.loads(resp_json)
            return resp
        except Exception as e:
            logger.exception(f'Error while extract json from LLM raw output: {e}')
            return {}
        
    def extract_code(self, full_response):
        if full_response.startswith("```java"):
            return full_response.split("```java")[1].split("```")[0]
        if full_response.startswith("```python"):
            return full_response.split("```python")[1].split("```")[0]
        return full_response.split("```")[1].split("```")[0]
    
    def token_count(self, input_str):
        input_tokens = self.tokenizer.encode(input_str)
        input_token_count = len(input_tokens)
        return input_token_count