
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.DomainEntity;

@Component
@Transactional
public class DomainEntityToStringConverter implements Converter<DomainEntity, String> {

	@Override
	public String convert(final DomainEntity d) {
		String result;

		if (d == null)
			result = null;
		else
			result = String.valueOf(d.getId());

		return result;
	}
}
