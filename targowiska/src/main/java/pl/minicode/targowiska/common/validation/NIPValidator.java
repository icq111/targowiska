package pl.minicode.targowiska.common.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import pl.minicode.targowiska.address.Address;

@Component
public class NIPValidator implements Validator {


	@Override
	public boolean supports(Class<?> clazz) {
		return Address.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if(target instanceof Address) {
			Address address = (Address) target;
			if( ! isNipValid(address.getNip())) {
				errors.rejectValue("nip", "address.validation.nip.invalid");
			}
		} else {
			errors.rejectValue("nip", "address.validation.nip.object.is.not.address");
		}

	}
	
	private boolean isNipValid(String nip) {
		if (nip.length() == 13) {
			nip = nip.replaceAll("-", "");
		}
		if (nip.length() != 10) {
			return false;
		}
		int[] weights = {6, 5, 7, 2, 3, 4, 5, 6, 7};
		try {
			int sum = 0;
			for (int i = 0; i < weights.length; ++i) {
				sum += Integer.parseInt(nip.substring(i, i + 1)) * weights[i];
			}
			return (sum % 11) == Integer.parseInt(nip.substring(9, 10));
		} catch (NumberFormatException e) {
			return false;
		}		
	}

}
