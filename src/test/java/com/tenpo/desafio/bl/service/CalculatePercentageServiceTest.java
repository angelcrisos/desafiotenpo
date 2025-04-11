package com.tenpo.desafio.bl.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.github.benmanes.caffeine.cache.Cache;
import com.tenpo.desafio.bl.client.PercentageClient;
import com.tenpo.desafio.bl.util.CalculateUtil;
import com.tenpo.desafio.exceptions.CacheServiceException;

@ExtendWith(MockitoExtension.class)
class CalculatePercentageServiceTest {

    private static final String PERCENTAGE = "percentage";
    
    @Mock
    private PercentageClient mockClient;
    
    @Mock
    private Cache<String, Double> mockCache;
    
    @InjectMocks
    private CalculatePercentageService calculatePercentageService;
    
    @Captor
    private ArgumentCaptor<Double> percentageCaptor;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCalculatePercentage_ValidPercentageFromClient() {
        double numberOne = 10.0;
        double numberTwo = 50.0;
        double mockPercentage = 20.0;
        
        when(mockClient.getPercentage()).thenReturn(mockPercentage);
        
        Double result = calculatePercentageService.calculatePercentage(numberOne, numberTwo);
        
        assertNotNull(result);
        assertEquals(CalculateUtil.calculateDynamicPercentage(numberOne, numberTwo, mockPercentage), result);
        verify(mockCache).put(eq(PERCENTAGE), percentageCaptor.capture());
        assertEquals(mockPercentage, percentageCaptor.getValue());
    }

    @Test
    void testCalculatePercentage_PercentageFromCacheWhenClientFails() {
        double numberOne = 10.0;
        double numberTwo = 50.0;
        double cachedPercentage = 15.0;
        
        when(mockClient.getPercentage()).thenThrow(new RuntimeException("Client error"));
        when(mockCache.getIfPresent(PERCENTAGE)).thenReturn(cachedPercentage);
        
        Double result = calculatePercentageService.calculatePercentage(numberOne, numberTwo);
        
        assertNotNull(result);
        assertEquals(CalculateUtil.calculateDynamicPercentage(numberOne, numberTwo, cachedPercentage), result);
        verify(mockCache, never()).put(anyString(), anyDouble());
    }

    @Test
    void testCalculatePercentage_ExceptionWhenCacheIsEmpty() {
        when(mockClient.getPercentage()).thenThrow(new RuntimeException("Client error"));
        when(mockCache.getIfPresent(PERCENTAGE)).thenReturn(null);
        
        assertThrows(CacheServiceException.class, () -> calculatePercentageService.calculatePercentage(10.0, 50.0));
    }

    @Test
    void testCalculatePercentage_ValidPercentageFromClient_ZeroValues() {
        double mockPercentage = 20.0;
        
        when(mockClient.getPercentage()).thenReturn(mockPercentage);
        
        Double result = calculatePercentageService.calculatePercentage(0.0, 0.0);
        
        assertNotNull(result);
        assertEquals(CalculateUtil.calculateDynamicPercentage(0.0, 0.0, mockPercentage), result);
        verify(mockCache).put(eq(PERCENTAGE), percentageCaptor.capture());
        assertEquals(mockPercentage, percentageCaptor.getValue());
    }
}