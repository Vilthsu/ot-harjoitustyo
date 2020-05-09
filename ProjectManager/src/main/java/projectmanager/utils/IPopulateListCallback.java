package projectmanager.utils;

import projectmanager.domain.IExampleData;

public interface IPopulateListCallback {
    IExampleData generateItem(int i);
}
