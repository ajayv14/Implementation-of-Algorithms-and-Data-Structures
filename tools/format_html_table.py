import re

def convert_to_pascal_case(kebab_case):
    # Convert kebab-case to PascalCase
    return ''.join(word.capitalize() for word in kebab_case.split('-'))

def process_leetcode_table(input_text):
    # Regular expression to match the problem URL and name
    pattern = r'<a href="(https://leetcode.com/problems/([^/"]+))'
    
    # Split input into lines
    lines = input_text.split('\n')
    output_lines = []
    counter = 1
    
    for line in lines:
        if '<td>' in line and 'leetcode.com' in line:
            # Find the problem name from URL
            match = re.search(pattern, line)
            if match:
                problem_url = match.group(1)
                problem_name = match.group(2).strip('/"')
                
                # Clean up the problem name
                problem_name = problem_name.replace('submissions', '')
                problem_name = problem_name.split('/')[0]  # Remove any trailing paths
                
                # Convert to PascalCase for Java filename
                class_name = convert_to_pascal_case(problem_name)
                
                # Create git URL
                git_url = f'AlgoApp/src/com/app/importantLC/{class_name}.java'
                
                # Add git URL column
                git_url_td = f'      <td><a href="{git_url}">git url</a></td>'
                
                # Fix the counter
                line = line.replace('<td>1</td>', f'<td>{counter}</td>')
                counter += 1
                
                # Remove closing tr if it exists
                line = line.replace('</tr>', '')
                
                # Add the git URL column and closing tr
                line = f'{line}\n{git_url_td}\n    </tr>'
                
        output_lines.append(line)
    
    return '\n'.join(output_lines)

# Example usage
input_text = """
<table>
    <tr>
      <th>No.</th>
      <th>Questions</th>
      <th>Difficulty</th>
      <th>Category</th>
      <th>Tags</th>
      <th>Git Url</th>
    </tr>
    <tr>
      <td>1</td>
      <td>
        <a href="https://leetcode.com/problems/median-of-two-sorted-arrays">median-of-two-sorted-arrays</a>
      </td>
      <td>3/5</td>
      <td></td>
      <td></td>
    </tr>
"""

print(process_leetcode_table(input_text))
