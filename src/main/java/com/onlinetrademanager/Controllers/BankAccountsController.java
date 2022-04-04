package com.onlinetrademanager.Controllers;

import com.onlinetrademanager.DataTransferObjects.BankAccounts.BankAccountEdit;
import com.onlinetrademanager.DataTransferObjects.BankAccounts.BankAccountInsert;
import com.onlinetrademanager.Models.BankAccount;
import com.onlinetrademanager.Services.BankAccountsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Controllers are the most exposed layer of the MVC architecture which is used for creating this application.
 * With them, we can access data in the database and fetch, add, modify, or delete it.
 * Controllers create addresses which are used by the front-end part of the application.
 * These addresses follow the format: http://localhost:8080/api/{table name}.
 * Spring applications run on the 8080 port by default.
 * Controllers' addresses redirect and trigger methods from the Service layer
 * We can pass parameters in a couple ways:
 * @PathVariable - requires the parameters to be set in the address string
 * @RequestBody - requires the parameters to be set in the JSON body
 * The current controller is used for operations on Bank Accounts.
 * **/

@RestController
@RequestMapping("/api/BankAccount")
public class BankAccountsController {
    private final BankAccountsService bankAccountsService;

    public BankAccountsController(BankAccountsService bankAccountsService) {
        this.bankAccountsService = bankAccountsService;
    }

    @PostMapping
    public ResponseEntity<UUID> insertBankAccount(@RequestBody BankAccountInsert bankAccountInsert) {
        UUID  bankAccountId = bankAccountsService.insertBankAccount(bankAccountInsert);
        return new ResponseEntity<>(bankAccountId, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<BankAccount>> getBankAccountList(@RequestParam UUID clientId) {
        List<BankAccount> bankAccounts = bankAccountsService.findAllByClient(clientId);
        return new ResponseEntity<>(bankAccounts, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Boolean> updateBankAccount(@RequestBody BankAccountEdit bankAccount) {
        boolean updated = bankAccountsService.updateBankAccount(bankAccount);
        if (updated) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        }
        else return new ResponseEntity<>(updated, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteBankAccount(@RequestParam UUID id) {
        bankAccountsService.deleteBankAccount(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
