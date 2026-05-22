English | [简体中文](FINDINGS.zh-CN.md)

# What the pay-run model showed

The generator applies every sequence of the four events (calculate, approve, pay, reverse) up to a given length to the lifecycle model. In this model an event that is not allowed leaves the state unchanged, so a history keeps going after a rejected event; a history counts as reaching the duplicate-approval defect when `APPROVE` arrives while the model is already `APPROVED`.

| Length | Histories (4^n) | Reaching a second approval |
| ---: | ---: | ---: |
| 3 | 64 | 1 |
| 5 | 1,024 | 91 |

At length 3 only `CALCULATE, APPROVE, APPROVE` reaches it. At length 5 the defect is no longer one special path: 91 histories reach it, many of them after events that were rejected along the way. A test suite built from a few hand-picked histories can therefore miss it, while the product's full transition matrix (every state against every event, with the rejected ones checked not to change the state) covers the second approval directly.

What this means for the product:

- Keep the transition matrix as the main test of `PayRun`; it is complete for five states and four events and is cheaper to read than generated histories.
- The enumeration is a design check: no library is needed for a space this small, and the count shows why the matrix matters. It does not test the product code itself.

The product decision is recorded in [ADR 0008: pay-run transition contract](https://github.com/codexary/payslate/blob/main/docs/adr/0008-payrun-transition-contract.md).
