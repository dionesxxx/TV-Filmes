# Arquitetura

Usa conceitos da arquitetura do Uncle Bob chamada [Clean Architecture]. <br />
O software/app produzido por esta arquitetura será:

* Independente de Frameworks.
* Testavel.
* Independente de UI.
* Independente de Database.

### A Regra de Dependência

A regra principal desta arquitetura diz que as dependências do código-fonte sempre apontam para dentro. <br />
Os níveis externos só podem depender de camadas internas. Portanto, as camadas internas não sabem nada sobre as camadas externas. <br />
Quanto mais você atravessa os círculos concêntricos, mais alto fica o software. O que significa que o nível de abstração aumenta.

### Entidades

Uma entidade é um conjunto de estruturas de dados. Essas entidades são os objetos de negócios do aplicativo e encapsulam as regras mais gerais e de alto nível, como [Genre] ou [Movie].

### Casos de Uso

Eles são as operações do aplicativo e podem conter regras de negócios específicas. <br />
Essa camada é isolada do banco de dados, da interface do usuário ou de qualquer estrutura comum. <br />
Todas as classes de caso de uso estendem a classe abstrata [UseCase] que configura os planejadores de Extensões reativas. <br />

[UseCase]: domain/src/main/java/br/diones/domain/UseCase.kt "Classe Use Case"
[Clean Architecture]: https://8thlight.com/blog/uncle-bob/2012/08/13/the-clean-architecture.html "The Clean Architecture by Robert C. Martin"
[Genre]: domain/src/main/java/br/diones/domain/entity/Genre.kt "Entidade Genre"
[Movie]: domain/src/main/java/br/diones/domain/entity/Movie.kt "Entidade Movie"
