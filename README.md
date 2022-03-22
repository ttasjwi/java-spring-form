
## java-spring-form

인프런 김영한 님의 '스프링 MVC 2편 - 백엔드 웹 개발 활용 기술'의 2단원 '스프링 통합과 폼'을 정리하기 위한 Repository

---

## 폼(Form) 처리

```html
<form action="item.html" th:action th:object="${item}" method="post">
    <div>
        <label for="itemName">상품명</label>
        <input type="text" id="itemName" th:field="*{itemName}" class="form-control" placeholder="이름을 입력하세요">
    </div>
    <div>
        <label for="price">가격</label>
        <input type="text" id="price" th:field="*{price}" class="form-control" placeholder="가격을 입력하세요">
    </div>
    <div>
        <label for="quantity">수량</label>
        <input type="text" id="quantity" th:field="*{quantity}" class="form-control" placeholder="수량을 입력하세요">
    </div>
```
```html
    <form action="item.html" th:action th:object="${item}" method="post">
        <div>
            <label for="id">상품 ID</label>
            <input type="text" id="id" class="form-control" th:field="*{id}"  readonly>
        </div>
```
- `th:object` : 폼과 관련된 객체 지정
- 선택 변수식 `*{...}` : 앞에서 th:object에서 지정한 객체의 프로퍼티와 같음.
  - 예> `th:object="${item}"` 일때 `th:object="*{itemName}"`은 `th:object="${item.itemName}"`와 같음
- `th:field` : 지정 변수에 맞게 id, name, value 생성
  - id : field에서 지정한 변수명
  - name : field에서 지정한 변수명
  - value : field에서 지정한 변수에 저장된 값

---
