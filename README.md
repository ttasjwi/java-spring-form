
## java-spring-form

인프런 김영한 님의 '스프링 MVC 2편 - 백엔드 웹 개발 활용 기술'의 2단원 '스프링 통합과 폼'을 정리하기 위한 Repository

---

## 폼(Form) 처리 - 기본

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

## 폼(Form) 처리 - 체크박스

### 스프링의 체크박스 처리 지원
```html
<input type="checkbox" id="open" name="open" class="form-check-input">
<input type="hidden" name="_open" value="on"/> <!-- 히든 필드 추가 -->
```
- 기본 HTML 폼의 체크박스는 체크되지 않을 경우 서버로 폼 데이터 전송 시 값 자체가 넘어오지 않는다.
- 폼에 히든필드를 하나 생성해서, 체크박스의 name 앞에 "_(언더바)"를 붙이면 체크가 해제되었을 때 이에 따른 boolean 값이 false로 들어온다.

### 타임리프의 체크박스 처리 지원
```html
<input type="checkbox" id="open" th:field="*{open}" class="form-check-input">
```
- `th:field` 속성을 이용하여 id, name, value 생성
- 히든필드도 자동으로 생성해주고 언더바 name 속성도 자동으로 추가해 줌

### 타임리프의 멀티체크박스 처리 지원
```html
<!-- multi checkbox -->
<div>
    <div>등록 지역</div>
    <div th:each="region : ${regions}" class="form-check form-check-inline">
        <input type="checkbox" th:field="*{regions}" th:value="${region.key}"
               class="form-check-input">
        <label th:for="${#ids.prev('regions')}"
               th:text="${region.value}" class="form-check-label">서울</label>
    </div>
</div>
```
- 타임리프는 each문으로 체크박스를 생성할 때 동적으로 id값을 뒤에 1,2,3 순서로 붙여줌
- `th:for="${#ids.prev('regions')}"`
  - 앞에서 동적으로 체크박스의 id가 생성되는데, label이 이를 추척할 수 있도록 타임리프에서는 `{#ids.prev(...)}`,`{#ids.next(...)}`를 지원함
- 실제로 상품 등록 요청을 하면 `@ModelAttribute`에 의해 Item 객체를 생성하여, 메서드를 호출한다. 이 때 체크박스에서 체크된 요소들이 전부 `item`의 필드에 해당하는 `regions(List<String>)`에 들어가게 된다. 체크된 요소가 없으면 아무 것도 들어가지 않는다.
  ```
  item.getRegions() = [SEOUL, JEJU]
  item.getRegions() = []
  ```
- 타임리프는 멀티 체크박스에 대하여, `th:field` 에 지정한 값과 `th:value`에 담긴 값을 비교하여 `checked="checked"`처리도 자동으로 해준다.

---

## 폼(Form) 처리 - 라디오 버튼

```html
<div>
    <div>상품 종류</div>
    <div th:each="type : ${itemTypes}" class="form-check form-check-inline">
        <input type="radio" th:field="*{itemType}" th:value="${type.name()}"
               class="form-check-input">
        <label th:for="${#ids.prev('itemType')}"
               th:text="${type.description}"
               class="form-check-label">상품종류명</label>
    </div>
</div>
```
- 체크박스와 구조적으로 동일
  - 라디오 버튼은 선택되어 있는 경우 수정 시에도 다른 하나를 반드시 선택해야하므로, 히든필드 처리를 안 해도 됨.
- 아무것도 체크하지 않을 경우 enum 기준 서버에는 null로 넘어간다. null로 넘어가는 것을 허용하지 않으려면 별도로 검증 제약 조건을 추가해주면 된다.

---

## 타임리프에서 enum 사용하기

```html
<div th:each="type : ${T(hello.itemservice.domain.item.ItemType)}" class="form-check form-check-inline">
```
- enum 클래스의 FQCN을 지정하여, enum을 사용하는 방법이 존재한다.
- 하지만, 추후 패키지 디렉토리 변경 시 html 파일도 건들여야하고... 자바가 컴파일러에서 타임리프 오류를 잡지 못 해낸다. 여러모로 유지보수 면에서 힘드므로 추천하지 않음.

---
