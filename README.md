
## build

go to the root directory

```bash
mvn clean install
```

you could find war package under `bean-frontend/target`

## modules

```
pom.xml
   - bean-service
   - bean-backend
   - bean-frontend
   - schema
```

### bean-backend

model and logic

- `src/main/java/common/bean`
  - config
    配置文件 （ie.properties）

  - constants
    全局常量

  - util
    全局工具类

  - model

### bean-service

web service


### bean-frontend

frontend pages (html, css, js, etc)

```
pom.xml
   - src/main/webapp
      - images
      - scripts (js)
      - styles (css)
      - views (html)
      - WEB-INF
      - console.html
      - home.html
      - test_upload.html
```

### schema

定义的数据


