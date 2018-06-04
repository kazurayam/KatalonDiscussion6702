import groovy.json.JsonSlurper
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

def atsakymas = '''
{"189":{"value":266,"label":"External Coordinator Course (EXCOORD-HL-EN) (EXCOORD-HL-EN)"}},
{"162":{"value":239,"label":"First Aid Course  (PMP-HL-EN)"}},
{"54":{"value":82,"label":"First Aid Course (FA-HL-EN) (FA-HL-EN)"}}
'''

// The string contained in the variable atsakymas is NOT a valid JSON.
def list = new JsonSlurper().parseText( atsakymas )
WebUI.comment("list=${list}")

// '[' + atsakumas + ']' makes a valid JSON
def wrapped = new JsonSlurper().parseText('[' + atsakymas + ']')
WebUI.comment("wrapped=${wrapped}")

def expectedLabel = 'First Aid Course  (PMP-HL-EN)'
def wantedValue = 0
// Here I assume the 'wrappeed' varaiable is an instance of java.util.List<Map>
for (Map map : wrapped) {
	Set<String> keySet = map.keySet()
	Iterator iter = keySet.iterator()
	while (iter.hasNext()) {
		def key = iter.next()
		WebUI.comment("key=${key}")
		def v = map.get(key)
		if (v.get('label') == expectedLabel) {
			wantedValue = v.get('value')
			WebUI.comment("wantedValue=${wantedValue} when key=${key}")
		}
	}
}
