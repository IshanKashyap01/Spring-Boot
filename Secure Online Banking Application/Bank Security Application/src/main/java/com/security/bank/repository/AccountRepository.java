package com.security.bank.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.security.bank.entity.Account;
import com.security.bank.entity.AccountType;
import com.security.bank.entity.BranchType;
import com.security.bank.entity.Nominee;
import com.security.bank.entity.User;

public interface AccountRepository extends JpaRepository<Account, Long>
{
    /**
     * Finds an account record with the given number
     * @param accountNumber account number
     * @return Optional with the account object
     */
    Optional<Account> findByAccountNumber(Long accountNumber);
    /**
     * Queries the database for accounts with an active status
     * @return list of active accounts
     */
    @Query
    (
        "select a from Account a where a.status = 'ACTIVE'"
    )
    List<Account> findAllActiveAccounts();
    /**
     * Queries the database for accounts with an inactive status
     * @return list of inactive accounts
     */
    @Query
    (
        "select a from Account a where a.status = 'INACTIVE'"
    )
    List<Account> findAllInactiveAccounts();
    /**
     * Queries the account table wrt the given account type
     * @param accountType type of accounts to look for
     * @return list of accounts with the given type
     */
    @Query
    (
        "select a from Account a where a.accountType = ?1"
    )
    List<Account> findAllByAccountType(AccountType accountType);
    /**
     * Queries the account table wrt the specified branch type
     * @param branchType type of branch to look for
     * @return list of accounts with the given branch type
     */
    @Query
    (
        "select a from Account a where a.branch = ?1"
    )
    List<Account> findAllByBranchType(BranchType branch);
    /**
     * Queries the database for the specified account's balance
     * @param accountNumber number of the account to be queried
     * @return account balance
     */
    @Query
    (
        "select a.balance from Account a where a.accountNumber = ?1"
    )
    double getBalance(Long accountNumber);
    /**
     * Finds accounts of a user
     * @param userId id of the user
     * @return list of user accounts
     */
    List<Account> findByUser_Id(Long userId);
    // User findUserByAccountNumber(Long accountNumber);
    @Query("SELECT a.nominee FROM Account a WHERE a.accountNumber = ?1")
    Nominee findNomineeByAccountNumber(Long accountNumber);

    @Query("SELECT a.user FROM Account a WHERE a.accountNumber = ?1")
    User findUserByAccountNumber(Long accountNumber);
}