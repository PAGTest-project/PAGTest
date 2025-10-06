from abc import ABC, abstractmethod
import tiktoken

class LLM(ABC):

    def calculate_tokens(self, messages, model):
        tokenizer = tiktoken.get_encoding("cl100k_base")  # This is an example; replace with the actual encoding used by your model
        total_tokens = 0
        for message in messages:
            content = message["content"]
            tokens = tokenizer.encode(content)
            total_tokens += len(tokens)
        return total_tokens