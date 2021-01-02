package pl.minicode.targowiska.address;

import pl.minicode.targowiska.common.IPageableCommonService;

public interface IAddressService extends IPageableCommonService<Address> {

	Address getLastRowInTable();
}
