package si.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class GenericConverter<T> {

	/*
	 * public List<T> convert(Class<T> lEntityClass, Hashtable<String, String>
	 * entities) { List<T> records = new ArrayList<>(); try { for (String
	 * field:entities.keySet()) {
	 * records.add(createLocalEntity(lEntityClass.newInstance(),
	 * entities.get(field))); } } catch (IllegalAccessException |
	 * InstantiationException e) { e.printStackTrace(); } return records; }
	 */

	public T createBoard(T board, Hashtable<String, String> entity) {
		Class<?> c = board.getClass();
		for (String key : entity.keySet()) {
			try {
				Field f = c.getDeclaredField(key);
				f.setAccessible(true);
				f.set(board, entity.get(key));
			} catch (NoSuchFieldException | IllegalAccessException e) {
			//	e.printStackTrace();
			}
		}
		return board;
	}
}
