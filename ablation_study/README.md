## Ablation Study of PAGTest

This folder contains the experimental data and statistical results from the ablation study of PAGTest. The generated tests are located in the `final_result` folder within each subfolder, and the statistical data can be found in the `statistics` folder of each subfolder.

### Overview of Experimental Setup

In this ablation study, we evaluate the impact of different phases of the GWT (Given-When-Then) strategy on the performance of PAGTest. We compare the following configurations:

- **Naive-Way**: The LLM directly receives the candidate methods' code to find relevant methods without considering the GWT structure.
- **No-Given**: Removes relevant methods identified by the **Given** phase.
- **No-When**: Removes relevant methods identified by the **When** phase.
- **No-Then**: Removes relevant methods identified by the **Then** phase.

**The experimental results exceeded 2GB, which is too large for the anonymous repository, so we have packed them into their respective archives.**

### Key Findings

The experimental results demonstrate the effectiveness of the **GWT-Strategy**, with the **Given** phase being the most critical for the performance of PAGTest. When any phase of the GWT is removed, the performance of PAGTest declines significantly, especially in terms of full coverage and success rates.

The following table summarizes the key metrics of the ablation study:

| Metric                | GWT         | Naive-Way  | No-G       | No-W      | No-T      |
|-----------------------|-------------|------------|------------|-----------|-----------|
| Error(Compile&Assert) | 603(39.8%) | 913(60.3%) | 1140(75.2%) | 1077(71.1%) | 1108(73.1%) |
| Success-Run               | 912(60.2%) | 602(39.7%) | 375(24.8%)  | 438(28.9%)  | 407(26.9%) |
| Full-Coverage         | 821(54.2%) | 487(32.1%) | 298(19.7%)  | 358(23.6%)  | 331(21.8%) |

### Conclusion

The results clearly demonstrate that the GWT strategy is effective in improving the correctness and completeness of the generated tests. The **Given** phase is the most critical, as removing it (No-Given) results in a significant drop in performance. The **Naive-Way** approach, while better than removing phases, still performs worse than the full GWT strategy due to its lack of phase-based filtering. Removing specific phases of the GWT strategy severely limits the ability of the model to generate relevant and accurate tests, as seen in the **No-Given**, **No-When**, and **No-Then** configurations.
