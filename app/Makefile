.PHONY: build

RESOURCES=./src/test/resources/fixtures

run-dist-json:
	./build/install/app/bin/app $(RESOURCES)/file1.json $(RESOURCES)/file2.json

run-dist-json-plain:
	./build/install/app/bin/app $(RESOURCES)/file1.json $(RESOURCES)/file2.json -f plain

run-dist-yml:
	./build/install/app/bin/app $(RESOURCES)/file1.yml $(RESOURCES)/file2.yml

build:
	./gradlew build

report:
	./gradlew jacocoTestReport