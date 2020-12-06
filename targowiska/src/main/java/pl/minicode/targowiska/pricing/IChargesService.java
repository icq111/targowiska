package pl.minicode.targowiska.pricing;

import java.util.List;

import pl.minicode.targowiska.common.IPageableCommonService;


public interface IChargesService extends IPageableCommonService<Charges> {

	List<Charges> findActiveCharges();
}
