package co.com.vulky.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ObjectMapper {

    private static ModelMapper modelMapper = new ModelMapper();

    static {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.STRICT);
    }

    private ObjectMapper() {
    }

    public static <D, T> D map(final T entity, Class<D> dto) {
        return entity != null ? modelMapper.map(entity, dto) : null;
    }

    public static <D, T> List<D> mapAll(final Collection<T> entityList, Class<D> dto) {
        return entityList.stream()
                .map(entity -> map(entity, dto))
                .collect(Collectors.toList());
    }

    public static <S, D> D map(final S source, D destination) {
        modelMapper.map(source, destination);
        return destination;
    }

}
