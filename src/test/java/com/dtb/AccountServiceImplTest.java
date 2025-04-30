package com.dtb;

import com.dtb.dto.CreateAccountRequest;
import com.dtb.dto.UpdateAccountRequest;
import com.dtb.entities.Account;
import com.dtb.implementation.AccountServiceImpl;
import com.dtb.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountServiceImpl accountService;

    private Account testAccount;

    @BeforeEach
    void setUp() {
        testAccount = new Account();
        testAccount.setId(1L);
        testAccount.setProfileId(100L);
        testAccount.setBalance(BigDecimal.valueOf(1000));
        testAccount.setActive(true);
    }

    @Test
    void createAccount_ShouldCreateNewAccount_WhenValidRequest() {
        // Arrange
        CreateAccountRequest request = new CreateAccountRequest();
        request.setProfileId(100L);
        request.setInitialBalance(BigDecimal.valueOf(500));

        when(accountRepository.save(any(Account.class))).thenReturn(testAccount);

        // Act
        Account result = accountService.createAccount(request);

        // Assert
        assertNotNull(result);
        assertEquals(100L, result.getProfileId());
        assertEquals(BigDecimal.valueOf(1000), result.getBalance());
        assertTrue(result.isActive());
        verify(accountRepository, times(1)).save(any(Account.class));
    }

    @Test
    void getBalance_ShouldReturnBalance_WhenAccountExists() {
        // Arrange
        when(accountRepository.findByProfileId(100L)).thenReturn(Optional.of(testAccount));

        // Act
        BigDecimal balance = accountService.getBalance(100L);

        // Assert
        assertEquals(BigDecimal.valueOf(1000), balance);
        verify(accountRepository, times(1)).findByProfileId(100L);
    }

    @Test
    void getBalance_ShouldThrowException_WhenAccountNotFound() {
        // Arrange
        when(accountRepository.findByProfileId(999L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> accountService.getBalance(999L));
        verify(accountRepository, times(1)).findByProfileId(999L);
    }

    @Test
    void activateAccount_ShouldSetActiveToTrue_WhenAccountExists() {
        // Arrange
        testAccount.setActive(false);
        when(accountRepository.findByProfileId(100L)).thenReturn(Optional.of(testAccount));
        when(accountRepository.save(any(Account.class))).thenReturn(testAccount);

        // Act
        Account result = accountService.activateAccount(100L);

        // Assert
        assertTrue(result.isActive());
        verify(accountRepository, times(1)).findByProfileId(100L);
        verify(accountRepository, times(1)).save(testAccount);
    }

    @Test
    void deactivateAccount_ShouldSetActiveToFalse_WhenAccountExists() {
        // Arrange
        when(accountRepository.findByProfileId(100L)).thenReturn(Optional.of(testAccount));
        when(accountRepository.save(any(Account.class))).thenReturn(testAccount);

        // Act
        Account result = accountService.deactivateAccount(100L);

        // Assert
        assertFalse(result.isActive());
        verify(accountRepository, times(1)).findByProfileId(100L);
        verify(accountRepository, times(1)).save(testAccount);
    }

    @Test
    void findById_ShouldThrowException_WhenAccountNotFound() {
        // Arrange
        when(accountRepository.findByProfileId(999L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> accountService.getBalance(999L));
        verify(accountRepository, times(1)).findByProfileId(999L);
    }
}
