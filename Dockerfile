# ===== ESTÁGIO 1: build (compila o projeto e gera o .jar) =====
FROM maven:3.9-eclipse-temurin-17 AS build

WORKDIR /app

# copia só o pom.xml primeiro -- assim o Docker reaproveita o cache das dependências
# se você só mudar código depois, não precisa baixar tudo de novo
COPY pom.xml .
RUN mvn dependency:go-offline

# agora copia o código-fonte e builda de verdade
COPY src ./src
RUN mvn clean package -DskipTests

# ===== ESTÁGIO 2: runtime (só o necessário pra RODAR, sem Maven, sem código-fonte) =====
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# copia SÓ o .jar final que foi gerado no estágio 1
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
