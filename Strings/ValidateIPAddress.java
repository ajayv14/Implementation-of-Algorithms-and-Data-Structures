class ValidateIPAddress {
    public String validIPAddress(String IP) {
        
        String result = "Neither";
        
        if(IP == null || IP.length() == 0 ||
                   IP.charAt(IP.length() - 1) == '.' || IP.charAt(IP.length() - 1) == ':') return result;
        
         /* validate for ipv4 */  
        if(IP.contains(".")){            
             
            String[] ipv4 = IP.split("[.]");        
                        
            if(ipv4.length > 4 || ipv4.length < 4 ) return result;
            
            for(String ipv4Component : ipv4){
                
                /*checks if ipv4 contains numeric values only*/
                if(ipv4Component.matches("[0-9]+") == false || ipv4Component.length() > 4) return result;
                   
                /*checks for 0 - 255 range*/
                int ipv4ComponentNumeric = Integer.parseInt(ipv4Component);
                if(ipv4ComponentNumeric < 0 || ipv4ComponentNumeric > 255) return result;
                
                /*checks for leading zero condition*/
                if(ipv4Component.length() > 1 && ipv4Component.charAt(0) == '0') return result;               
            }            
            
            return "IPv4";            
            
        } else if(IP.contains(":")){  /* validate for IPv6 */
            
            
            String[] ipv6 = IP.split(":");
            
            if(ipv6.length < 8 || ipv6.length > 8) return result;
            
            for(String ipv6Component : ipv6){                       
                /*checks for hexadecimal condition : a-f, A-F , 0-9*/
                if( ipv6Component.matches("[a-fA-F0-9]+") == false || ipv6Component.length() > 4) return result;                
            }            
            
            return "IPv6";
        }
        
        return result;       
        
    }    
}