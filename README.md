# gencode
generate boilerplate code for Java web development, inspired by Ruoyi.

base on UML,not based on Java class,nor Database table.
从小处出发做到极致。

1. 新增表的情况
2. 加字段的情况
3. 如何兼容不同的命名方式，比如有的是驼峰有的是纯小写有的是下划线
4. 如何兼容不容的功能需求，比如有的生成的代码不需要导出excel功能，同时如果不维护多个模板呢？在同一个模板文件中太多条件判断也会影响可读性

## todo
- [ ] 使用表单创建属性，能生成代码和表创建语句
- [ ] 使用DDD模式，生成的代码合并到一个文件夹中
- [ ] 独立于程序生成代码包，生成代码的程序不再开发的业务代码中
- [ ] 可以做成基于浏览器的服务
- [ ] 使用内存数据库比如 H2，而不是需要另外安装的mysql等