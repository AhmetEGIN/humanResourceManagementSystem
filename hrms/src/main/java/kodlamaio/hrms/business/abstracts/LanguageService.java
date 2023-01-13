package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.business.requests.languageRequests.CreateLanguageRequest;
import kodlamaio.hrms.business.responses.languageResponses.GetAllLanguageResponse;
import kodlamaio.hrms.business.responses.languageResponses.GetLanguageResponse;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;

public interface LanguageService {
	Result add(CreateLanguageRequest languageRequest);
	DataResult<List<GetAllLanguageResponse>> getAll();
	DataResult<GetLanguageResponse> getById(int id);
}
