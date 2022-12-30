find ~/ -maxdepth 1 -type d -name 'apache-jmeter-*' | xargs -I {} find {} -name "jmeter" | head -n 1 | xargs -I {} sh {} -n -t ./GetStudyPerfTest.jmx
