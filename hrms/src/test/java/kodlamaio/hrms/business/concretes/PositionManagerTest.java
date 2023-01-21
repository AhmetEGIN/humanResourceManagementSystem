package kodlamaio.hrms.business.concretes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import kodlamaio.hrms.business.requests.positionRequests.CreatePositionRequest;
import kodlamaio.hrms.core.utilities.mapping.ModelMapperManager;
import kodlamaio.hrms.dataAccess.abstracts.PositionRepository;
import kodlamaio.hrms.entities.concretes.Position;

@ExtendWith(MockitoExtension.class)
public class PositionManagerTest {
	
	//Test class ının bulunduğu paket adresi test edilmek istenen class'ın paket uzantısı ile aynı olmalıdır
	// Unit Test -- bir projedeki her bir küçük parçanın kod yazılarak test edilmesidir. 
	// Bu da kodu daha kontrollü bir hale getirir.
	// Kısa sürede birkaç bin satır test edilebilir.
	// Testler kodun çalıştırılabilir örnek dökümantasyonunu oluşturur.
	// Hataların daha kolay bulunabilmesi ve düzeltilebilmesini sağlar
//	@InjectMocks
	private PositionManager positionManager;
	// InjectMocks -- Instance oluşturur. Ve bunun üzerinde ihtiyaç duyulan başka nesneler varsa onların da instance'ını oluşturur
	
	@Mock
	private PositionRepository positionRepository;
	// Mock -- çalışma zamanında onun gerçek olmayan kopyasını yaratır.
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		positionManager  = new PositionManager(positionRepository, new ModelMapperManager(new ModelMapper()));
		
	}
	
	
	@Test
	public void testAdd() {
		CreatePositionRequest request = new CreatePositionRequest();
		request.setName("Test-name");
		Position positionMock = Mockito.mock(Position.class);
		
		Mockito.when(positionRepository.save(any(Position.class))).thenReturn(positionMock);
		
		
		boolean result = positionManager.add(request).isSuccess();
		assertTrue(result);
//		assert 
		
	}
	
	
}
