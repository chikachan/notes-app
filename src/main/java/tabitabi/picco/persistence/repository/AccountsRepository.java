package tabitabi.picco.persistence.repository;

import org.springframework.data.repository.CrudRepository;

import tabitabi.picco.model.Account;

public interface AccountsRepository extends CrudRepository<Account, Long> {
	
	Account findByEmail(String email);

}
