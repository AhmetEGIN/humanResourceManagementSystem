package kodlamaio.hrms.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.GithubAccountService;
import kodlamaio.hrms.business.constants.messages.Message;
import kodlamaio.hrms.business.requests.githubAccountRequests.CreateGithubAccountRequest;
import kodlamaio.hrms.business.requests.githubAccountRequests.UpdateGithubAccountRequest;
import kodlamaio.hrms.business.responses.githubAccountResponses.GetAllGithubAccountResponse;
import kodlamaio.hrms.business.responses.githubAccountResponses.GetGithubAccountResponse;
import kodlamaio.hrms.core.utilities.business.BusinessRules;
import kodlamaio.hrms.core.utilities.mapping.ModelMapperService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.GithubAccountRepository;
import kodlamaio.hrms.entities.concretes.GithubAccount;

@Service
public class GithubAccountManager implements GithubAccountService {

	private GithubAccountRepository githubAccountRepository;
	private ModelMapperService mapperService;
	
	@Autowired
	public GithubAccountManager(GithubAccountRepository githubAccountRepository, ModelMapperService mapperService) {
		this.githubAccountRepository = githubAccountRepository;
		this.mapperService = mapperService;
	}

	@Override
	public Result add(CreateGithubAccountRequest githubAccountRequest) {
		var result = BusinessRules.run(checkIfLinkContainsDomain(githubAccountRequest.getAccountAddress()));
		if (result != null) {
			return new ErrorResult();
		}
		GithubAccount githubAccount = this.mapperService.forRequest().map(githubAccountRequest, GithubAccount.class);
		githubAccount.setId(0);
		this.githubAccountRepository.save(githubAccount);
		return new SuccessResult();
	}

	@Override
	public Result update(int id, UpdateGithubAccountRequest githubRequest) {
		var result  = BusinessRules.run(checkAccountExist(id));
		if (result != null) {
			return new ErrorResult();
		}
		GithubAccount githubAccount = this.githubAccountRepository.getReferenceById(id);
		updateAccount(githubAccount, githubRequest);
		this.githubAccountRepository.save(githubAccount);
		return new SuccessResult();
	}

	@Override
	public DataResult<List<GetAllGithubAccountResponse>> getAll() {
		List<GetAllGithubAccountResponse> accountResponses = this.githubAccountRepository.findAll().stream().map(account->this.mapperService.forResponse().map(account, GetAllGithubAccountResponse.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<GetAllGithubAccountResponse>>(accountResponses);
	}

	@Override
	public DataResult<GetGithubAccountResponse> getById(int id) {
		GetGithubAccountResponse accountResponse = this.mapperService.forResponse().map(githubAccountRepository.findById(id), GetGithubAccountResponse.class);
		return new SuccessDataResult<GetGithubAccountResponse>(accountResponse);
	}
	
	// private codes -- business rules
	
	private Result checkIfLinkContainsDomain(String accountAddress) {
		if (accountAddress.contains("https://github.com/") || accountAddress.contains("github.com/")) {
			return new SuccessResult();
		}
		return new ErrorResult(Message.CHECK_ACCOUNT_ADDRESS);
	}
	
	private Result checkAccountExist(int id) {
		if (this.githubAccountRepository.existsGithubAccountByIdEquals(id)) {
			return new SuccessResult();
		}
		return new ErrorResult(Message.ACCOUNT_NOT_FOUND);
	}
	
	private void updateAccount(GithubAccount githubAccount, UpdateGithubAccountRequest updateGithubRequest) {
		githubAccount.setAccountAddress(updateGithubRequest.getAccountAddress());
	}

}
