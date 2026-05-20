[English](README.md) | 简体中文

# 工资批次模型原型

一个不依赖任何库的生成器：列举长度不超过指定值的全部工资批次事件历史，逐条送进五状态的生命周期模型（草稿、已计算、已批准、已付款、已冲正）。五种状态、四种事件的规模足够小，可以穷举，迁移规则和历史条数都便于审阅。如果某条历史在模型已处于 `APPROVED` 时又收到 `APPROVE`，就记为到达重复批准缺陷。

用 Java 21 构建并运行：

```sh
./mvnw -q package
java -cp target/classes com.elsworne.payslate.spike.modelbased.SequenceGenerator 5
```

穷举只用来检验产品测试的设计，不能代替产品测试：产品仍要保留完整的迁移矩阵，包括必须拒绝、且不能改变状态的事件。穷举的结果见 [FINDINGS.zh-CN.md](FINDINGS.zh-CN.md)。

## 关于本仓库

Elsworne、Payslate 和团队成员均为虚构。

本仓库的代码与文档借助 AI 工具编写；记录的输出都来自实际运行。
