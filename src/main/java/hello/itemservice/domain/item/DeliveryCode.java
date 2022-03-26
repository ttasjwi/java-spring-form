package hello.itemservice.domain.item;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 배송
 */
@RequiredArgsConstructor
@Getter
public class DeliveryCode {
    /**
     * FAST: 빠른 배송
     * NORMAL: 일반 배송
     * SLOW: 느린 배송
     */
    private final String code; // 시스템에서 전달하는 값. FAST, NORMAL, SLOW
    private final String displayName; // 사용자에게 표시하는 값. 빠른 배송, 일반 배송, 느린 배송
}
