<p align="center">
  <b>Reference-Based Retrieval-Augmented Unit Test Generation for Java</b>
</p>

<p align="center">
  Official implementation of our ACM TOSEM paper on reference-based retrieval-augmented unit test generation.
</p>

<p align="center">
  <a href="https://dl.acm.org/doi/pdf/10.1145/3765758">Paper</a> •
  <a href="#quick-start">Quick Start</a> •
  <a href="#mcp-tools">MCP Tools</a> •
  <a href="#citation">Citation</a>
</p>

---

## Overview

**PAGTest** is a research prototype for **LLM-based unit test generation for Java projects**.

Unlike conventional approaches that generate tests only from the focal method, PAGTest augments generation with **reference test knowledge** retrieved from the same repository. It identifies methods whose existing tests can serve as useful references, then combines those references with the focal method's static context to generate higher-quality unit tests.

The key intuition is simple:

> Good tests already exist elsewhere in the project.  
> Instead of generating from scratch, PAGTest learns from them.

---

## Key Features

- **Reference-based retrieval-augmented test generation**
- **Given–When–Then aware reference reasoning**
- **Static context construction for focal methods**
- **Reusable test knowledge extraction from existing tests**
- **Cross-class reference discovery** through inheritance and interfaces
- **Incremental generation strategy** that reuses newly generated tests
- **MCP server support** for tool-based interaction with LLM agents

---

## How PAGTest Works

PAGTest follows a multi-stage workflow:

1. **Parse the repository**
   - Parse Java source files into AST-based metadata.

2. **Build metainfo**
   - Construct structured metadata for classes, methods, interfaces, and test classes.

3. **Analyze existing test cases**
   - Use LLMs to analyze existing tests and extract reusable test knowledge.

4. **Retrieve reference methods**
   - Find methods whose tests can provide useful guidance for the target method.

5. **Generate new unit tests**
   - Combine static context and retrieved references to generate tests.

6. **Incremental reuse**
   - Newly generated tests can be fed back into the system as additional references.

This design allows PAGTest to move beyond isolated test generation and instead exploit **repository-wide testing knowledge**.

---

## Why PAGTest?

In real-world repositories, many methods share:

- similar initialization logic
- similar invocation patterns
- similar assertions
- similar exception handling behavior

PAGTest models these reusable patterns through the **Given–When–Then (GWT)** perspective:

- **Given**: object setup, fixtures, preconditions
- **When**: method invocation and execution flow
- **Then**: assertions, state checks, exception validation

By retrieving tests that are useful for one or more of these phases, PAGTest provides LLMs with much stronger and more actionable context than raw source code alone.

---

## Repository Structure

```text
.
├── repo_parse/
│   ├── analysis/
│   ├── common/
│   ├── context_retrieval/
│   ├── entrypoint/
│   ├── generator/
│   ├── llm/
│   ├── metainfo/
│   ├── parser/
│   ├── prompt/
│   ├── property_graph/
│   ├── scope_graph/
│   ├── utils/
│   ├── config.py
│   └── run.py
├── requirements.txt
└── README.md
````

---

## Installation

### Requirements

* Python **3.10+**
* A valid LLM API endpoint
* A target **Java project** containing source files under `src/main/java`

Install dependencies:

```bash
pip install -r requirements.txt
```

Create a `.env` file in the project root:

```env
API_BASE=https://your-llm-api.com/v1
API_KEY=your_api_key_here
MODEL_NAME=deepseek-coder
```

---

## Quick Start

PAGTest is designed for **Java repositories**.

Your target project should look like this:

```text
/path/to/your-java-project
└── src/main/java
```

PAGTest will automatically create a `.PAGTest/` directory under the target repository to store metadata, analysis results, logs, and generated outputs.

### 1. Set `PYTHONPATH`

```bash
export PYTHONPATH=$(pwd):$PYTHONPATH
```

### 2. Start the MCP server

```bash
python repo_parse/entrypoint/mcp_server.py
```

---

## MCP Tools

PAGTest currently exposes four core MCP tools:

| Tool                | Description                                                           | Async | Uses LLM |
| ------------------- | --------------------------------------------------------------------- | ----- | -------- |
| `parse_repo`        | Parse a Java repository into AST-based metadata                       | No    | No       |
| `build_metainfo`    | Build structured metadata for classes, methods, interfaces, and tests | No    | No       |
| `analyze_testcases` | Analyze existing test cases and build reusable test knowledge         | Yes   | Yes      |
| `generate_testcase` | Generate a test case for a target Java method                         | Yes   | Yes      |

### Recommended Usage Order

1. `parse_repo`
2. `build_metainfo`
3. `analyze_testcases`
4. `generate_testcase`

---

## Tool Details

### 1. `parse_repo`

Parse a Java project into structured AST metadata.

#### Input

```json
{
  "repo_path": "/path/to/your-java-project"
}
```

#### Output

```json
{
  "success": true,
  "message": "Repository parsed successfully. Metadata saved under /path/to/your-java-project/.PAGTest/"
}
```

---

### 2. `build_metainfo`

Build structured metadata for classes, methods, interfaces, and test classes.

#### Input

```json
{
  "repo_path": "/path/to/your-java-project"
}
```

#### Output

```json
{
  "success": true,
  "message": "Metainfo built successfully."
}
```

#### Typical generated files

* `class_metainfo.json`
* `method_metainfo.json`
* `testclass_metainfo.json`
* `interface_metainfo.json`

---

### 3. `analyze_testcases`

Analyze existing test files with an LLM and build reusable reference knowledge.

#### Input

```json
{
  "repo_path": "/path/to/your-java-project",
  "is_batch": true,
  "filter_list": [
    "src/test/java/com/example/FooTest.java",
    "src/test/java/com/example/BarTest.java"
  ]
}
```

#### Notes

* This is a **long-running asynchronous task**
* It consumes **LLM tokens**
* It produces artifacts used later by `generate_testcase`

#### Typical generated files

* `.PAGTest/testcase_analysis_result.json`
* `.PAGTest/method_to_primary_testcase.json`
* `.PAGTest/node_coordinator_result.json`

---

### 4. `generate_testcase`

Generate a test case for a target Java method.

#### Input

```json
{
  "repo_path": "/path/to/your-java-project",
  "target_class": "ArrayStack",
  "target_method": "[int]search(Object)"
}
```

#### Parameters

* `target_class`: simple class name, e.g. `ArrayStack`
* `target_method`: method signature, e.g. `[int]search(Object)`

To locate valid method signatures, inspect `.PAGTest/method_metainfo.json`, for example:

```json
{
  "uris": "org.apache.commons.collections4.ArrayStack.[int]search(Object)"
}
```

#### Notes

* `analyze_testcases` should be completed before generation
* Each call generates a test for **one target method**
* Generated test code is returned as text and should be integrated and validated manually

---

## Output Directory

PAGTest stores intermediate artifacts and outputs under `.PAGTest/` inside the target repository.

```text
your-java-project/
├── src/
├── .PAGTest/
│   ├── all_metainfo.json
│   ├── class_metainfo.json
│   ├── method_metainfo.json
│   ├── testcase_analysis_result.json
│   ├── method_to_primary_testcase.json
│   ├── generated_testcases/
│   └── logs/
└── ...
```

---

## Design Rationale

The multi-stage pipeline is intentional.

* **`parse_repo`** gives the system structural visibility into the codebase
* **`build_metainfo`** extracts retrieval-ready metadata
* **`analyze_testcases`** transforms existing tests into reusable test knowledge
* **`generate_testcase`** uses all prior outputs to produce better tests

In other words, the first three stages construct a **retrieval-ready and reasoning-ready test knowledge base** for the final generation stage.

---

## Paper

If you use PAGTest in your research or engineering workflow, please refer to our paper:

**Reference-Based Retrieval-Augmented Unit Test Generation**
ACM Transactions on Software Engineering and Methodology (TOSEM)

Paper link:
[https://dl.acm.org/doi/pdf/10.1145/3765758](https://dl.acm.org/doi/pdf/10.1145/3765758)

---

## Citation

```bibtex
@article{zhang2025pagtest,
  title={Reference-Based Retrieval-Augmented Unit Test Generation},
  author={Zhang, Zhe and Liu, Xingyu and Lin, Yuanzhang and Gao, Xiang and Sun, Hailong and Yuan, Yuan},
  journal={ACM Transactions on Software Engineering and Methodology},
  year={2025},
  doi={10.1145/3765758}
}
```

---

## Limitations

* Currently supports **Java only**
* Relies on external LLM APIs for test analysis and test generation
* Generated tests may still require compilation, validation, and manual refinement

---

## License
MIT
