package hello.itemservice.domain.item;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 상품 종류
 */
@RequiredArgsConstructor
@Getter
public enum ItemType {

    BOOK("도서"), FOOD("음식"), ETC("기타");

    private final String description;

}
