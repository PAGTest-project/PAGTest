# PAGTest: Property-Based Retrieval Augmentation for Unit Test Generation
This repository contains the tools and experimental results mentioned in the paper.

# Experiment
## Experiment Process Record
Directory: `experiment/statistics`  
This directory contains experimental data records for 12 projects. Taking `experiment/statistics/jsoup-master` as an example, each project's experimental data includes:
- `generated_testcases`: All generated unit tests, including successful and failed ones.
- `method_property`: JSON files of property relationships generated for target methods.
- `running_status`: The running status of each target method's generated unit tests.
- `testclass_analysis_result`: Analysis results of the generated unit tests.

Other JSON files store various metainfo and intermediate results of metainfo processing.

We provide a script to replicate the experimental statistics: `PAGTest/repo_parse/test/testutils.py`. You can run it directly to obtain the results, which are highly accurate.

## Generated Unit Tests
The generated unit tests for the 12 projects are located in the `experiment/final_result` directory. Each project is independently runnable.  
For each project, the `bad_testfile_archive` directory stores the test cases that failed after generation. Therefore, all generated unit tests in the project, except those in this directory, are runnable.

## Ablation Study

We conducted detailed ablation studies to explore the effectiveness of each strategy. See [Ablation Study](./ablation_study/README.md) for more details.

# Tool
## How to Run? 
### Prerequisites
- Python version: 3.10.14
- Run `pip install -r requirements.txt`

### Configuration
#### LLM Configuration
Create a `.env` file in the `PAGTest` directory:
```
DEEPSEEK_API_KEY="xxxx"
DEEPSEEK_API_BASE="https://api.deepseek.com"
DEEPSEEK_MODEL="deepseek-coder"
```
Other models can be easily replaced as long as they comply with the OpenAI specification.

#### Config File Configuration
- `REPO_PATH`: Absolute path to the target project, e.g., `REPO_PATH = r"~/targets/java/commons-collections-3"`
- `PACKAGE_PREFIX`: e.g., `PACKAGE_PREFIX = "org.apache.commons.collections4"`

With these configurations, the tool is ready to run, making the setup process straightforward and efficient.

### Run
```python
python PAGTest/repo_parse/run.py
```
