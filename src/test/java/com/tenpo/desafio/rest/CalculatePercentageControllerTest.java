package com.tenpo.desafio.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.tenpo.desafio.bl.service.AsyncLogService;
import com.tenpo.desafio.bl.service.CalculatePercentageService;
import com.tenpo.desafio.domain.dto.CalculateRequestDto;
import com.tenpo.desafio.domain.dto.CallHistoryDto;
import com.tenpo.desafio.exceptions.CacheServiceException;

import jakarta.servlet.http.HttpServletRequest;

@ExtendWith(MockitoExtension.class)
public class CalculatePercentageControllerTest {

    @Mock
    private CalculatePercentageService calculateService;

    @Mock
    private AsyncLogService logService;

    @Mock
    private HttpServletRequest request;

    @InjectMocks
    private CalculatePercentageController controller;

    @BeforeEach
    public void setup() {
        when(request.getRequestURL()).thenReturn(new StringBuffer("http://localhost/api/calculatepercentage"));
        when(request.getQueryString()).thenReturn("num1=10&num2=20");
    }

    @Test
    public void testGetCalculatePercentageSuccess() {
    	
        CalculateRequestDto requestDto = CalculateRequestDto.builder().num1(10.0).num2(20.0).build();
        when(calculateService.calculatePercentage(10.0, 20.0)).thenReturn(30.0);

        ResponseEntity<Double> response = controller.getCalculatePercentage(requestDto, request);
        verify(logService, times(1)).save(any(CallHistoryDto.class));

        assertEquals(200, response.getStatusCode().value());
        assertEquals(30.0, response.getBody());
    }

    @Test
    public void testGetCalculatePercentageCacheServiceException() {
        CalculateRequestDto requestDto = CalculateRequestDto.builder().num1(10.0).num2(20.0).build();
        doThrow(new CacheServiceException("Error message")).when(calculateService).calculatePercentage(10.0, 20.0);

        CacheServiceException exception = assertThrows(CacheServiceException.class, () -> {
            controller.getCalculatePercentage(requestDto, request);
        });

        assertEquals("Error message", exception.getMessage());
        verify(logService, times(1)).save(any(CallHistoryDto.class));
    }

    

    
}