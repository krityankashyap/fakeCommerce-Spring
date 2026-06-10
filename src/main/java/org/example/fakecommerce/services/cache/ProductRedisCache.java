package org.example.fakecommerce.services.cache;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.fakecommerce.dtos.GetOrderResponseDto;
import org.example.fakecommerce.dtos.GetProductResponseDto;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductRedisCache {

    private static final String Key_Summary= "product:summary";

    private final StringRedisTemplate stringRedisTemplate;

    private final ObjectMapper objectMapper;

    public Optional<GetProductResponseDto> getSummary(Long id){
        String responseJson= stringRedisTemplate.opsForValue().get(Key_Summary + id);

        if(responseJson== null) return Optional.empty();  // Cache miss

        // Cache hit
        try{
            GetProductResponseDto response= objectMapper.convertValue(responseJson, GetProductResponseDto.class);
            return Optional.of(response);
        } catch (Exception e) {
            log.error("Error parsing product summary from cache: {}", e.getMessage());
            stringRedisTemplate.delete(Key_Summary + id);  // because the data is corrupted
            return Optional.empty();
        }
    }


    public void putSummary(Long id, GetProductResponseDto response){
        try{
            stringRedisTemplate.opsForValue().set(Key_Summary + id, objectMapper.writeValueAsString(response));
        } catch (Exception e) {
            throw new RuntimeException("Error serializing product summary to cache: " + e.getMessage());
        }
    }
}
