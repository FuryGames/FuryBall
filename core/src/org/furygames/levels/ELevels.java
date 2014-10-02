package org.furygames.levels;

public enum ELevels {
    LEVEL8 (null),
    LEVEL7 (LEVEL8),
	LEVEL6 (LEVEL7),
	LEVEL5 (LEVEL6),
	LEVEL4 (LEVEL5),
	LEVEL3 (LEVEL4), 
	LEVEL2 (LEVEL3), 
	LEVEL1 (LEVEL2);
	
	private final ELevels next;
	
	private ELevels (ELevels next) {
		this.next = next;
	}
	
	public ELevels next () {
		return next == null ? LEVEL8 : next;
	}
}
