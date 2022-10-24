package com.rank.ccms.util;

import java.io.Serializable;
import org.hibernate.type.Type;

public final class EntityMetaData implements Serializable {

    private String qualifiedClassName;
    private String tableName;
    private String entityClassName;
    private String entityNameActual;
    private Type entityColumnType;
    private String rootEntityName;
    private String rootTableName;
    private Integer rootTableKeyColumnsLength;
    private String[] rootTableKeyColumnsNames;
    private Integer rootTableIdentifierColumnLength;
    private String[] rootTableIdentifierColumnNames;
    private String identifierPropertyName;
    private Integer identifierColumnLength;
    private String[] identifierColumnNames;
    private Type identifierColumnType;
    private Integer primaryKeyColumnLength;
    private String[] primaryKeyColumnNames;
    private String mappedSuperclassName;
    private Integer propertyNamesLength;
    private String[] propertyNames;
    private Integer propertyColumnNames1Length;
    private String[] propertyColumnNames1;

    public String getQualifiedClassName() {
        return qualifiedClassName;
    }

    public void setQualifiedClassName(String qualifiedClassName) {
        this.qualifiedClassName = qualifiedClassName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getEntityClassName() {
        return entityClassName;
    }

    public void setEntityClassName(String entityClassName) {
        this.entityClassName = entityClassName;
    }

    public String getEntityNameActual() {
        return entityNameActual;
    }

    public void setEntityNameActual(String entityNameActual) {
        this.entityNameActual = entityNameActual;
    }

    public Type getEntityColumnType() {
        return entityColumnType;
    }

    public void setEntityColumnType(Type entityColumnType) {
        this.entityColumnType = entityColumnType;
    }

    public String getRootEntityName() {
        return rootEntityName;
    }

    public void setRootEntityName(String rootEntityName) {
        this.rootEntityName = rootEntityName;
    }

    public String getRootTableName() {
        return rootTableName;
    }

    public void setRootTableName(String rootTableName) {
        this.rootTableName = rootTableName;
    }

    public Integer getRootTableKeyColumnsLength() {
        return rootTableKeyColumnsLength;
    }

    public void setRootTableKeyColumnsLength(Integer rootTableKeyColumnsLength) {
        this.rootTableKeyColumnsLength = rootTableKeyColumnsLength;
    }

    public String[] getRootTableKeyColumnsNames() {
        return rootTableKeyColumnsNames;
    }

    public void setRootTableKeyColumnsNames(String[] rootTableKeyColumnsNames) {
        this.rootTableKeyColumnsNames = rootTableKeyColumnsNames;
    }

    public Integer getRootTableIdentifierColumnLength() {
        return rootTableIdentifierColumnLength;
    }

    public void setRootTableIdentifierColumnLength(Integer rootTableIdentifierColumnLength) {
        this.rootTableIdentifierColumnLength = rootTableIdentifierColumnLength;
    }

    public String[] getRootTableIdentifierColumnNames() {
        return rootTableIdentifierColumnNames;
    }

    public void setRootTableIdentifierColumnNames(String[] rootTableIdentifierColumnNames) {
        this.rootTableIdentifierColumnNames = rootTableIdentifierColumnNames;
    }

    public String getIdentifierPropertyName() {
        return identifierPropertyName;
    }

    public void setIdentifierPropertyName(String identifierPropertyName) {
        this.identifierPropertyName = identifierPropertyName;
    }

    public Integer getIdentifierColumnLength() {
        return identifierColumnLength;
    }

    public void setIdentifierColumnLength(Integer identifierColumnLength) {
        this.identifierColumnLength = identifierColumnLength;
    }

    public String[] getIdentifierColumnNames() {
        return identifierColumnNames;
    }

    public void setIdentifierColumnNames(String[] identifierColumnNames) {
        this.identifierColumnNames = identifierColumnNames;
    }

    public Type getIdentifierColumnType() {
        return identifierColumnType;
    }

    public void setIdentifierColumnType(Type identifierColumnType) {
        this.identifierColumnType = identifierColumnType;
    }

    public Integer getPrimaryKeyColumnLength() {
        return primaryKeyColumnLength;
    }

    public void setPrimaryKeyColumnLength(Integer primaryKeyColumnLength) {
        this.primaryKeyColumnLength = primaryKeyColumnLength;
    }

    public String[] getPrimaryKeyColumnNames() {
        return primaryKeyColumnNames;
    }

    public void setPrimaryKeyColumnNames(String[] primaryKeyColumnNames) {
        this.primaryKeyColumnNames = primaryKeyColumnNames;
    }

    public String getMappedSuperclassName() {
        return mappedSuperclassName;
    }

    public void setMappedSuperclassName(String mappedSuperclassName) {
        this.mappedSuperclassName = mappedSuperclassName;
    }

    public Integer getPropertyNamesLength() {
        return propertyNamesLength;
    }

    public void setPropertyNamesLength(Integer propertyNamesLength) {
        this.propertyNamesLength = propertyNamesLength;
    }

    public String[] getPropertyNames() {
        return propertyNames;
    }

    public void setPropertyNames(String[] propertyNames) {
        this.propertyNames = propertyNames;
    }

    public Integer getPropertyColumnNames1Length() {
        return propertyColumnNames1Length;
    }

    public void setPropertyColumnNames1Length(Integer propertyColumnNames1Length) {
        this.propertyColumnNames1Length = propertyColumnNames1Length;
    }

    public String[] getPropertyColumnNames1() {
        return propertyColumnNames1;
    }

    public void setPropertyColumnNames1(String[] propertyColumnNames1) {
        this.propertyColumnNames1 = propertyColumnNames1;
    }

}
