
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import domain.DomainEntity;

@Component
@Transactional
public class StringToDomainEntityConverter implements Converter<String, DomainEntity> {

	@Autowired
	DomainEntityRepository	domainEntityRepository;


	@Override
	public DomainEntity convert(final String s) {
		DomainEntity result;
		int id;

		try {
			if (StringUtils.isEmpty(s))
				result = null;
			else {
				id = Integer.valueOf(s);
				result = this.domainEntityRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}
}
