MARP := marp
PREFIX := docs

OBJECTS := $(PREFIX)/index.html 


all: $(OBJECTS)

$(PREFIX)/%.html: %.md
	$(MARP) $< -o $@ 

.PHONY: clean

clean:
	rm -f $(PREFIX)/*.html
