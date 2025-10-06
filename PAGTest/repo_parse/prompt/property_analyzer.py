Prompt = """
Given the source code of a class, analyze the method [target_method_name] to identify opportunities for test case enhancements. Focus on two aspects:

1. **Direct Enhancements**: 
   - Identify methods in the class or external methods that are relevant to [target_method_name] in one or more of the following ways:
     - **Structural Similarity**: Methods that have similar internal structures and implementation patterns but perform different operations or functionalities. For example, `serialize` vs. `deserialize`, `sendGetRequest` vs. `sendPostRequest`, or `readFile` vs. `writeFile` methods that share similar control flow, parameter handling, and exception management but handle different types of operations.
     - **Behavioral Similarity**: Methods that achieve similar external behaviors or functionalities but operate on different data or contexts. For example, `fetchAccountName` vs. `fetchAccountBalance` methods that both retrieve account-related information but return different types of data.
     - **Substitutability**: Methods that serve the same purpose but with different implementations, where the test cases for one method can be applied to the other.
     - **Exception Handling Similarity**: Methods that handle exceptions in similar ways.
     - **Resource Access Similarity**: Methods that access or manipulate similar resources.
     - **Dependency**: Methods that depend on or modify the same underlying dependencies, such as shared services, databases, or states.
   - For each enhancement, provide the method name, the relation type (e.g., StructuralSimilarity, BehavioralSimilarity, Substitutability), the confidence level (0-1), whether the method is external (`is_external`), and a brief reason for the recommendation. If the method is external, also include the class name.

2. **GWT Enhancements**:
   - **Given**: Identify methods (internal or external) with similar input patterns, object initialization, or dependencies that can be reused in setting up tests for [target_method_name].
   - **When**: Highlight methods (internal or external) that must be invoked before or after [target_method_name] due to state dependencies or execution order.
   - **Then**: Suggest methods (internal or external) with similar output or behavior that can inform the construction of assertions for [target_method_name].
   - Not that we mentioned dependencies multiple times because DI (Dependency Injection) or other forms of reference dependencies are very common in the code.

Return the results in the following JSON structure:
```json
{
  "target_method": "validateURL",
  "signature": "boolean validateURL(String)",
  "direct_enhancements": [
    {
      "method_name": "parseURL(String)",
      "relation_type": "Behavioral Similarity",
      "confidence": 0.9,
      "reason": "Both methods process URL strings but perform different validations.",
      "is_external": false
    },
    {
      "method_name": "encodeURL(String)",
      "relation_type": "Substitutability",
      "confidence": 0.7,
      "reason": "Both methods encode URLs, but implemented in different external libraries.",
      "is_external": true,
      "class_name": "ExternalClass"
    },
    {
      "method_name": "serialize(Object)",
      "relation_type": "Structural Similarity",
      "confidence": 0.85,
      "reason": "Both methods handle data conversion with similar control flows and exception handling.",
      "is_external": false
    }
  ],
  "gwt_enhancements": {
    "Given": {
      "enhanced_by": [
        {
          "method_name": "initializeURLParser()",
          "relation_type": "State Change Similarity",
          "confidence": 0.8,
          "reason": "Both methods require URL parser initialization.",
          "is_external": false
        }
      ]
    },
    "When": {
      "enhanced_by": [
        {
          "method_name": "setupEnvironment()",
          "relation_type": "Resource Access Similarity",
          "confidence": 0.6,
          "reason": "Environment setup is required before validating URLs.",
          "is_external": false
        }
      ]
    },
    "Then": {
      "enhanced_by": [
        {
          "method_name": "verifyURLFormat()",
          "relation_type": "Exception Handling Similarity",
          "confidence": 0.85,
          "reason": "Both methods verify URL formats and handle similar exceptions.",
          "is_external": false
        }
      ]
    }
  }
}
```

## Note
- each method_name should be formatted as FunctionName(type_of_parameter1,type_of_parameter2), e.g. myFunction(int,String)>
- **Confidence**: A value between 0 and 1 indicating the confidence level of the enhancement relationship. Base this on factors such as code similarity, functional overlap, and dependency strength.
- Ensure that each enhanced method is unique across the Given, When, and Then sections to avoid redundancy.
- Provide clear and concise justifications for each enhancement to aid in understanding the rationale behind the recommendations.
"""