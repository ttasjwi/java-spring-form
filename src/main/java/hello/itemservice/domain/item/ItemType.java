package hello.itemservice.domain.item;

import lombok.RequiredArgsConstructor;

/**
 * 상품 종류
 */
@RequiredArgsConstructor
public enum ItemType {

    BOOK("도서"), FOOD("음식"), ETC("기타");

    private final String description;

}
