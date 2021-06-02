package org.silverbulleters.bsl.platform.context.types;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlatformTypeIdentifierTest {

  @Test
  void test() {
    check(PlatformTypeIdentifier.ADD_IN_OBJECT, "ADD_IN_OBJECT", "AddInObject", "ОбъектВнешнейКомпоненты");
    check(PlatformTypeIdentifier.MD_COMMAND_GROUP, "MD_COMMAND_GROUP", "MDCommandGroup", "МДГруппаКоманд");
    check(PlatformTypeIdentifier.COMMAND_GROUP, "COMMAND_GROUP", "CommandGroup", "ГруппаКоманд");
    check(PlatformTypeIdentifier.UNKNOWN, "", "", "");
  }

  private void check(PlatformTypeIdentifier value, String id, String name, String nameRu) {
    assertThat(value.getId()).isEqualTo(id);
    assertThat(value.getName()).isEqualTo(name);
    assertThat(value.getNameRu()).isEqualTo(nameRu);

    var valueById = PlatformTypeIdentifier.valueById(id);
    assertThat(valueById).isEqualTo(value);
  }

}
