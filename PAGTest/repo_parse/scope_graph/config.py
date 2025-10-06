
LANGUAGE = "python"
# LANGUAGE = "java"

PYTHONTS_LIB = "./languages/python/libs/my-python.so"
PYTHON_SCM = "./languages/python/python.scm"
PYTHON_REFS = "./languages/python/python_refs.scm"

FILE_GLOB_ENDING = {"python": ".py"}

SUPPORTED_LANGS = {"python": "python"}

NAMESPACE_DELIMETERS = {"python": "."}

SYS_MODULES_LIST = "./languages/{lang}/sys_modules.json".format(lang=LANGUAGE)

THIRD_PARTY_MODULES_LIST = (
    "./languages/{lang}/third_party_modules.json".format(lang=LANGUAGE)
)
