English | [简体中文](README.zh-CN.md)

# Pay-run model prototype

A dependency-free generator that enumerates every history of pay-run events up to a chosen length and runs each through the five-state lifecycle model (draft, calculated, approved, paid, reversed). Five states and four events are small enough to enumerate exhaustively, so the transition model and the number of histories stay easy to review. A history is counted as reaching the duplicate-approval defect when `APPROVE` arrives while the model is already `APPROVED`.

Build and run with Java 21:

```sh
./mvnw -q package
java -cp target/classes com.elsworne.payslate.spike.modelbased.SequenceGenerator 5
```

The enumeration is a design check for the product's tests, not a replacement for them: the product keeps the full transition matrix, including rejected events that must not change the state.

## About this repository

Elsworne, Payslate and the team are fictional.

The code and documents in this repository were written with the help of AI tools; every recorded output comes from actually running them.
