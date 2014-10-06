package com.bean.model;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Daniel on 14-8-17.
 */
@XmlRootElement
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class EntityList<T extends Entity> extends ArrayList<T> implements Cloneable{
    public EntityList() {
        super();
    }

    /**
     *  Find differences between current entity list and the old one.
     */
    public static void computeDiff(EntityList currList,
                                   EntityList oldList,
                                   EntityList addList,
                                   EntityList updateList,
                                   EntityList deleteList,
                                   boolean isEditMode){

        if(!isEditMode){
            //create and create_like mode
            if (currList != null && currList.size() > 0){
                for(int i = 0; i < currList.size(); i++){
                    addList.add((Entity)currList.get(i));
                }
            }
        }else{
            //edit mode
            if(currList == null || currList.size() == 0){
                //remove all
//                deleteList = oldList;
                for(int i = 0; i < oldList.size(); i++){
                    deleteList.add((Entity)oldList.get(i));
                }
            }else{
                HashMap<String, Entity> matchedRefMap = new HashMap<String, Entity>();
                for(int i = 0; i < currList.size(); i++){
                    Entity currEntity = (Entity)currList.get(i);
                    Entity matchedEntity = null;
                    for(int j  = 0; j < oldList.size(); j++){
                        Entity oldEntity = (Entity)oldList.get(j);
                        if(currEntity.getPrimaryKeyAsString().equals(oldEntity.getPrimaryKeyAsString())){
                            //the same pk, break out
                            matchedEntity = oldEntity;
                            matchedRefMap.put(oldEntity.getPrimaryKeyAsString(), oldEntity);
                            break;
                        }
                    }
                    if(matchedEntity == null){
                        //not matched, this is a new entity
                        if(addList == null)
                            addList = new EntityList<Entity>();
                        addList.add(currEntity);
                    }else{
                        if(!matchedEntity.equals(currEntity)){
                            //edit entity
                            if(updateList == null)
                                updateList = new EntityList<Entity>();
                            updateList.add(currEntity);
                        }
                    }
                }
                for(int j  = 0; j < oldList.size(); j++){
                    Entity o = (Entity)oldList.get(j);
                    if(!matchedRefMap.containsKey(o.getPrimaryKeyAsString())){
                        //delete entity
                        if(deleteList == null)
                            deleteList = new EntityList<Entity>();
                        deleteList.add(o);
                    }
                }
            }
        }
    }

    public List<T> convertToList(){
        List<T> list = new ArrayList<T>();

        for(T t : this){
            list.add(t);
        }

        return list;
    }

    public void convertToEntityList(List<T> list){
        for(T t : list){
            this.add(t);
        }
    }
}
