package kata5p1;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class EmailLoader implements Loader {
    private final Loader loader;
    private static final Pattern emailPattern = 
            Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    
    public EmailLoader(Loader loader) {
        this.loader = loader;
    }
    
    @Override
    public List<String> load() {
        List<String> list = new ArrayList<>();
        for (String line : loader.load())
            if (this.isEmail(line)) list.add(line);
        return list;
    }

    private boolean isEmail(String line) {
        return emailPattern.matcher(line).matches();
    }

}