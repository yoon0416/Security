// TrustScoreService.java
package com.janus.security.service;

import org.springframework.stereotype.Service;

@Service
public class TrustScoreService {

    // 신뢰 점수 계산 메서드
    public int calculateTrustScore(double responseTime, int urlAccessCount, boolean captureAttempt) {
        int trustScore = 100; // 기본 점수

        // 클릭 반응 속도 (초 단위, 0.5초 이상 시 감점)
        if (responseTime > 0.5) {
            trustScore -= 40;
        }

        // 직접 URL 접근 횟수 (3회 이상 시 감점)
        if (urlAccessCount > 3) {
            trustScore -= 30;
        }

        // 관리자 뷰 캡처 시도 시 감점
        if (captureAttempt) {
            trustScore -= 30;
        }

        return Math.max(trustScore, 0); // 점수는 0 이하로 내려가지 않음
    }

    // 샌드박스 격리 여부 판단
    public boolean shouldIsolate(int trustScore) {
        int threshold = 50; // 논문에 명시된 임계값 가정
        return trustScore < threshold;
    }
}
