package com.mini.auction;

import com.mini.auction.common.CustomNamingStrategy;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Table;
import jakarta.persistence.metamodel.EntityType;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.google.common.base.CaseFormat;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class DatabaseClearup implements InitializingBean {
    @PersistenceContext
    private EntityManager entityManager;

    private List<String> tableNames;

    @Override
    public void afterPropertiesSet() {
        CustomNamingStrategy a = new CustomNamingStrategy();
        // JPA Entity를 모두 가져옴
        final Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();
        // stream을 돌면서 Entity 어노테이션이 붙어있는지 확인
        tableNames = entities.stream()
            .filter(e -> isEntity(e) && hasTableAnnotation(e))
            .map(e -> {
                String tableName = e.getJavaType().getAnnotation(Table.class).name();
                // table 어노테이션이 있는지 확인해서 이름을 모두 리스트에 담음 -> 현 프로젝트 상황에 맞게 substring 규칙 적용
                return tableName.isBlank() ? CaseFormat.UPPER_CAMEL.to(
                    CaseFormat.LOWER_UNDERSCORE, e.getName()
                    ).substring(0, e.getName().length() - 6) : tableName;
            })
            .collect(Collectors.toList());
        // 위의 삼항 연산자 내용과 겹치는 것으로 보여서 주석 처리
//
//        final List<String> entityNames = entities.stream()
//            .filter(e -> isEntity(e) && !hasTableAnnotation(e))
//            .map(e -> CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, e.getName()))
//            .toList();
//
//        tableNames.addAll(entityNames);
    }

    private boolean isEntity(final EntityType<?> e) {
        return null != e.getJavaType().getAnnotation(Entity.class);
    }

    private boolean hasTableAnnotation(final EntityType<?> e) {
        return null != e.getJavaType().getAnnotation(Table.class);
    }

    @Transactional
    public void execute() {
        entityManager.flush();
        // FK 무시하는 설정
        entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY FALSE").executeUpdate();

        for (final String tableName : tableNames) {
            entityManager.createNativeQuery("TRUNCATE TABLE " + tableName).executeUpdate();
            entityManager.createNativeQuery("ALTER TABLE " + tableName + " ALTER COLUMN ID RESTART WITH 1").executeUpdate();
        }

        entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY TRUE").executeUpdate();
    }

}
