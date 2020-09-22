package ua.testing.blockchain.dto.mapper;

/**
 * @author Vendelovskyi Ivan
 * @version 1.0
 */
@FunctionalInterface
public interface Mapper<E, Dto> {
    Dto map(E entity);
}
