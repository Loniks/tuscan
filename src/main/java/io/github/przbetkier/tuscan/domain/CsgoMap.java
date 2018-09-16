package io.github.przbetkier.tuscan.domain;

public enum CsgoMap {
    DE_DUST2("de_dust2"),
    DE_CBBLE("de_cbble"),
    DE_TRAIN("de_train"),
    DE_NUKE("de_nuke"),
    DE_MIRAGE("de_mirage"),
    DE_CACHE("de_cache"),
    DE_OVERPASS("de_overpass"),
    DE_INFERNO("de_inferno"),
    DE_SEASON("de_season");

    private String name;

    CsgoMap(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
