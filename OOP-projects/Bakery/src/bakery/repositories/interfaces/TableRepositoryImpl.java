package bakery.repositories.interfaces;

import bakery.entities.tables.interfaces.Table;

import java.util.ArrayList;
import java.util.Collection;

public class TableRepositoryImpl implements TableRepository<Table> {
    private Collection<Table> models;

    public TableRepositoryImpl() {
        this.models = new ArrayList<>();
    }

    @Override
    public Table getByNumber(int number) {
        Table tableToReturn = null;
        for (Table model : models) {
            if (model.getTableNumber() == number) {
                tableToReturn = model;
            }
        }
        return tableToReturn;
    }

    @Override
    public Collection<Table> getAll() {
        return this.models;
    }

    @Override
    public void add(Table table) {
        models.add(table);
    }
}
