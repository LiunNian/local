package cn.org.cpcca.paperserver.servcie;

import cn.org.cpcca.paperserver.models.ItemModel;
import cn.org.cpcca.paperserver.models.ItemTypeModel;

import java.util.List;
import java.util.Map;

public interface ItemServiceInterface {
    int addItem(ItemModel itemModel);

    ItemModel selectItem(int id);

    int updateItem(ItemModel itemModel);

    List<ItemModel> listItemAll();

    List<ItemModel> listItemReview();

    List<ItemModel> listItems();

    List<ItemModel> listItemEnd();

    int updateState(Map<String,Object> itemMap);

    int deleteItems(List<Integer> itids);

    List<ItemTypeModel> listItemType();

    List<ItemModel> listItemNow();

    List<ItemModel> listItemBin();

    String getTypeName(int id);


    /**
     * 根据项目id导出论文-->查询管理数据
     * @param itemId
     * @return
     */
    String getFileList(int itemId);
}
